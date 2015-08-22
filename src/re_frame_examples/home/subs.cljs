(ns re-frame-examples.home.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as rf]))

(rf/register-sub
 :home-name
 (fn [state]
   (reaction (:name @state))))
