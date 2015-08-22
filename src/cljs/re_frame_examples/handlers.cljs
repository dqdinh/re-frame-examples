(ns re-frame-examples.handlers
  (:require [re-frame.core :as rf]
            [re-frame-examples.db :as db]))

(defn initialize-db
  [_ _]
  db/initial-state)

(rf/register-handler
  :initialize-db
  initialize-db)

(defn set-active-panel
  [db [_ active-panel]]
  (assoc db :active-panel active-panel))

(rf/register-handler
  :set-active-panel
  set-active-panel)
