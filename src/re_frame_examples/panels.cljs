(ns re-frame-examples.panels
    (:require [re-frame.core :as rf]
              [re-frame-examples.home.views :refer [ home-panel ]]
              [re-frame-examples.about.views :refer [ about-panel ]]
              [re-frame-examples.phonecat.panel :refer [ phonecat-panel ]]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defmulti panels identity)
(defmethod panels :default [] [:div "No panel"])
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :phonecat-panel [] [phonecat-panel])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [v-box
       :height "100%"
       :children [(panels @active-panel)]])))

(defn top-panel
  []
  (let [ready? (rf/subscribe [:initialized?])]
    (fn []
      (if @ready?
        [main-panel]
        [throbber]))))
