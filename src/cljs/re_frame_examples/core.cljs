(ns re-frame-examples.core
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.handlers]
              [re-frame-examples.subs]
              [re-frame-examples.state]
              [re-frame-examples.routes :refer [app-routes]]
              [re-frame-examples.panels :refer [top-panel]]))

(defn mount-root []
  (r/render [top-panel]
            (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch [:initialize-core-state])
  (app-routes)
  (mount-root))
