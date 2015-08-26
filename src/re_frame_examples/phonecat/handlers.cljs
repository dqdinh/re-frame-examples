(ns re-frame-examples.phonecat.handlers
    (:require [re-frame.core :as rf]
              [re-frame-examples.phonecat.state :refer [merge-phonecat-state]]
              [ajax.core :as ajax]))

(rf/register-handler
 :set-image
 (fn
   ;; take an image url and set it in the state
   [app-state [_ selected-image-url]]
   (assoc-in app-state [:phone-details :selected-image-url] selected-image-url)))

(rf/register-handler
 :process-phones-response
 (fn
   ;; store the response of fetching the phones list in the phones attribute of the state
   [app-state [_ response]]
   (assoc-in app-state [:phones] response)))

(rf/register-handler
 :process-phones-bad-response
 (fn
   ;; log a bad response fetching the phones list
   [app-state [_ response]]
   app-state))

(rf/register-handler
 :load-phones
 (fn
   ;; Fetch the list of phones and process the response
   [app-state _]
   (ajax/GET "phones/phones.json"
                  {:handler #(rf/dispatch [:process-phones-response %1])
                   :error-handler #(rf/dispatch [:process-phones-bad-response %1])
                   :response-format :json
                   :keywords? true})
   app-state))

(rf/register-handler
 :process-phone-detail-response
 (fn
   ;; store info for the specific phone-id in the state
   [app-state [_ phone-id response]]
   (assoc-in app-state [:phone-details (keyword phone-id)] response)))

(rf/register-handler
 :process-phone-detail-bad-response
 (fn
   [app-state [_ [phone-id response]]]
   (println "Error getting phone detail for id: " phone-id)
   (println response)
   app-state))

(rf/register-handler
 :load-phone-detail
 (fn
   ;; fetch information for the phone with the given phone-id
   [app-state [_ phone-id]]
   (ajax/GET (str "phones/" phone-id ".json")
             {:handler #(rf/dispatch [:process-phone-detail-response phone-id %1])
              :error-handler #(rf/dispatch [:process-phone-detail-bad-response phone-id %1])
              :response-format :json
              :keywords? true})
   app-state))

(rf/register-handler
 :initialize-phonecat-state
 merge-phonecat-state)

(defn handle-search-input-entered
  [app-state [_ search-input]]
  (assoc-in app-state [:search-input] search-input))

(defn handle-order-prop-changed
  [app-state [_ order-prop]]
  (assoc-in app-state [:order-prop] order-prop))

(rf/register-handler
 :search-input-entered
 handle-search-input-entered)

(rf/register-handler
 :order-prop-changed
 handle-order-prop-changed)
