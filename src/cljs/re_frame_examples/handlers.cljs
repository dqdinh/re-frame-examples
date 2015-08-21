(ns re-frame-examples.handlers
    (:require [re-frame.core :as rf]
              [re-frame-examples.db :as db]))

(rf/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(rf/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
