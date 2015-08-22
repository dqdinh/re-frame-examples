(ns re-frame-examples.views.home
    (:require [re-frame.core :as rf]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defn home-title []
  (let [name (rf/subscribe [:name])]
    (fn []
      [title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  [v-box
   :gap "1em"
   :children [[home-title] [link-to-about-page]]])
