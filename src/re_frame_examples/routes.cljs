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

(defn redirect-to
  [resource]
  (s/dispatch! resource)
  (.setToken (History.) resource))

(defn app-routes []
  (s/set-config! :prefix "#")

  ;; define routes here
  (s/defroute "/" []
    (rf/dispatch [:set-active-panel :home-panel]))

  (s/defroute "/about" []
    (rf/dispatch [:set-active-panel :about-panel]))

  (s/defroute "/phones" []
    (session/put! :current-page #'home-page))

  (s/defroute "/phones/:phone-id" {:as params}
    (session/put! :current-page #'phone-page)
    (session/put! :params params)
    (rf/dispatch [:load-phone-detail (:phone-id params)]))

  (s/defroute "*" []
    (redirect-to "/phones"))

  ;; History must be called after routes have been defined
  (hook-browser-navigation!))
