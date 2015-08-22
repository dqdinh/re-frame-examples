(ns re-frame-examples.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as rf]))

(rf/register-sub
 :initialised?
 (fn [db]
   (reaction (not (empty? @db)))))

(rf/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(rf/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))
