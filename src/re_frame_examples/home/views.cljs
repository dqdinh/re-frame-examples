(ns re-frame-examples.home.views
    (:require [re-frame.core :as rf]
              [re-frame-examples.home.handlers]
              [re-frame-examples.home.subs]
              [re-frame-examples.home.state]
              [re-com.core :refer [v-box throbber p title hyperlink-href ]]))

(defn home-title []
  (let [name (rf/subscribe [:home-name])]
    (fn []
      [title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn link-to-phonecat-page []
  [hyperlink-href
   :label "go to Phonecat Page"
   :href "#/phones"])


(defn home-panel []
  (rf/dispatch [:init-home-state])
  [:div {:class "container-fluid"}
     [:div [ home-title ]]
     [:div [ link-to-about-page ]]
     [:div [ link-to-phonecat-page ]]])
