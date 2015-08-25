(ns re-frame-examples.phonecat.routes
    (:require [reagent.session :as session]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [secretary.core :as s :include-macros true]
              [re-frame.core :as rf])
    (:import goog.History))

(s/set-config! :prefix "#")

(s/defroute "/phones" []
  (session/put! :current-page #'home-page))

(s/defroute "/phones/:phone-id" {:as params}
  (session/put! :current-page #'phone-page)
  (session/put! :params params)
  (rf/dispatch [:load-phone-detail (:phone-id params)]))

(defn redirect-to
  [resource]
  (s/dispatch! resource)
  (.setToken (History.) resource))

(s/defroute "*" []
  (redirect-to "/phones"))

(defn current-page []
  [(session/get :current-page) (session/get :params)])

;; History must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (s/dispatch! (.-token event))))
    (.setEnabled true)))

