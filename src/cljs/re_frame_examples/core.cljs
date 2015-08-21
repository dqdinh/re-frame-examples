(ns re-frame-examples.core
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.handlers]
              [re-frame-examples.subs]
              [re-frame-examples.routes :as routes]
              [re-frame-examples.views :as views]))

(defn mount-root []
  (r/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  ;; https://github.com/Day8/re-frame/wiki/Bootstrap-An-Application#a-cheat
  (rf/dispatch-sync [:initialize-db])
  (mount-root))
