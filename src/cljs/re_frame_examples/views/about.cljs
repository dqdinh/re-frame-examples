(ns re-frame-examples.views.about
    (:require [re-frame.core :as rf]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defn about-title []
  [title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])
