(ns re-frame-examples.panels
    (:require [re-frame.core :as rf]
              [re-frame-examples.home.views :as home]
              [re-frame-examples.about.views :as about]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defmulti panels identity)
(defmethod panels :default [] [:div "No panel"])
(defmethod panels :home-panel [] [home/home-panel])
(defmethod panels :about-panel [] [about/about-panel])

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
