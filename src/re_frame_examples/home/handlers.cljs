(ns re-frame-examples.home.handlers
  (:require [re-frame.core :as rf]
            [re-frame-examples.home.state :refer [initial-home-state]]))

(defn fold-home-state
  [state _]
  (into state initial-home-state))

(rf/register-handler
  :fold-home-state
  fold-home-state)
