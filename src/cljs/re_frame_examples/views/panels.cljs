(ns re-frame-examples.views.panels
    (:require [re-frame.core :as rf]
              [re-frame-examples.views.home :refer [ home-panel ]]
              [re-frame-examples.views.about :refer [ about-panel ]]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div "No panel"])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [v-box
       :height "100%"
       :children [(panels @active-panel)]])))

(defn top-panel
  []
  (let [ready? (rf/subscribe [:initialised?])]
    (fn []
      (if @ready?
        [main-panel]
        [throbber]))))
