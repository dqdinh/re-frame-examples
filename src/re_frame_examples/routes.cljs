(ns re-frame-examples.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [reagent.session :as session]
              [secretary.core :as s]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as rf]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (s/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (s/set-config! :prefix "#")

  ;; define routes here
  (s/defroute "/" []
    (rf/dispatch [:set-active-panel :home-panel]))

  (s/defroute "/about" []
    (rf/dispatch [:set-active-panel :about-panel]))

  (s/defroute "/phones" []
    (rf/dispatch [:set-active-panel :phonecat-panel]))

  ;; History must be called after routes have been defined
  (hook-browser-navigation!))
