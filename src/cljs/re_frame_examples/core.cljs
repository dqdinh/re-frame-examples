(ns re-frame-examples.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frame-examples.handlers]
              [re-frame-examples.subs]
              [re-frame-examples.routes :as routes]
              [re-frame-examples.views :as views]))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
