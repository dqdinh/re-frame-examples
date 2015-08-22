(ns re-frame-examples.handlers
  (:require [re-frame.core :as rf]
            [re-frame-examples.state :as state]))

(defn initialize-core-state
  [_ _]
  state/initial-state)

(rf/register-handler
  :initialize-core-state
  initialize-core-state)

(defn set-active-panel
  [state [_ active-panel]]
  (assoc state :active-panel active-panel))

(rf/register-handler
  :set-active-panel
  set-active-panel)