(ns re-frame-examples.core
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.handlers]
              [re-frame-examples.subs]
              [re-frame-examples.routes :as routes]
              [re-frame-examples.views.panels :as panels]))

(defn mount-root []
  (r/render [panels/top-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch [:initialize-db])
  (routes/app-routes)
  (mount-root))
