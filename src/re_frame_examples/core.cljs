(ns re-frame-examples.core
    (:require [reagent.core :as r]
              [re-frame.core :as rf]
              [re-frame-examples.utils :refer [mlog]]
              [re-frame-examples.handlers]
              [re-frame-examples.subs]
              [re-frame-examples.state]
              [re-frame-examples.routes :refer [app-routes]]
              [re-frame-examples.panels :refer [top-panel]])
    (:require-macros [re-frame-examples.env :refer [cljs-env]]))

(enable-console-print!)

(defn mount-root []
  (r/render [top-panel]
            (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch [:initialize-app-state])
  (app-routes)
  (mount-root))

(mlog "env" (cljs-env :env?))

(defn app-state-to-js
  "Used for inspecting app state in the console."
  [state]
  (clj->js state))

(defn show-state []
  (let [debug-state (rf/subscribe [:debug-state])]
    (app-state-to-js @debug-state)))
