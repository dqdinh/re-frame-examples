(ns re-frame-examples.panels
    (:require [re-frame.core :as rf]
              [re-frame-examples.home.views :refer [ home-panel ]]
              [re-frame-examples.about.views :refer [ about-panel ]]
              [re-frame-examples.phonecat.panels :refer [ phonecat-panel phonecat-details-panel ]]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

;; --- Panel Navigation Multimethod Declarations ---
(defmulti panels
  (fn [args] (:active-panel args)))

;; --- Panel Navigation Multimethod Implementations ---
(defmethod panels :default [] [:div "No panel"])

(defmethod panels :home-panel [] [home-panel])

(defmethod panels :about-panel [] [about-panel])

(defmethod panels :phonecat-panel [] [phonecat-panel])

(defmethod panels :phonecat-details-panel
  [args] [(phonecat-details-panel (get-in args [:panel-params :phone-id]))])

;; --- Top Level Panels ---
(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])
        panel-params (rf/subscribe [:panel-params])]
    (fn []
      [:div {:class "container-fluid"}
       [:div (panels {:active-panel @active-panel
                      :panel-params @panel-params})]])))

(defn top-panel
  []
  (let [ready? (rf/subscribe [:initialized?])]
    (fn []
      (if @ready?
        [main-panel]
        [throbber]))))
