(ns re-frame-examples.phonecat.routes
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

(defn phonecat-routes []
  ;; Add routes
  (s/defroute "/phones/:phone-id" {:as params}
    (rf/dispatch [:load-phone-detail (:phone-id params)]))

  ;; History must be called after routes have been defined
  (hook-browser-navigation!))
