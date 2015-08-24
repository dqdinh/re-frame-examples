(ns re-frame-examples.phonecat.views.phone-list
    (:require [reagent.core :as r]
              [re-frame.core :as rf]))

(defn phone-component
  "individual phone component in the phoens list view"
  [phone]
  [:li {:class "thumbnail phone-listing"}
   [:a {:href (str "#/phones/" (:id phone))
        :class "thumb"}
    [:img {:src (:imageUrl phone)}]]
   [:a {:href (str "#/phones/" (:id phone))} (:name phone)]
   [:p (:snippet phone)]])

(defn matches-query?
  "checks if the search input matches a name or snippet of the given phone"
  [search-input phone]
  (if (= "" search-input)
    true
    (boolean (or
              (re-find (re-pattern search-input) (:name phone))
              (re-find (re-pattern search-input) (:snippet phone))))))

(defn phones-component
  "component for the list of phones"
  []
  (let [phones (rf/subscribe [:phones])
        search-input (rf/subscribe [:search-input])
        order-prop (rf/subscribe [:order-prop])]
    (fn []
      [:ul {:class "phones"}
       (for [phone (->> @phones
                        (filter (partial matches-query? @search-input))
                        (sort-by (keyword @order-prop)))]
         ^{:key (:name phone)} [phone-component phone])])))

(defn search-component
  "component for the search input"
  []
  (let [search-input (rf/subscribe [:search-input])])
  (fn []
    [:div "Search"
     [:input {:on-change #(rf/dispatch [:search-input-entered (-> % .-target .-value)])}]]))

(defn mark-selected
  "mark the given select element as selected if the order-prop matches the value of the element passed in"
  [props order-prop current-prop-value]
  (if (= order-prop current-prop-value)
    (r/merge-props props {:selected "selected"})
    props))

(defn order-by-component
  "component to define how you want to order the phones list"
  []
  (let [order-prop (rf/subscribe [:order-prop])]
    (fn []
      [:div "Sort by: "
       [:select {:on-change #(rf/dispatch [:order-prop-changed (-> % .-target .-value)])}
        [:option (mark-selected {:value "name"} @order-prop "name") "Alphabetical"]
        [:option (mark-selected {:value "age"} @order-prop "age") "Newest"]]])))

(defn home-page
  "defines the ome page which will be the phone list component"
  []
  [:div {:class "container-fluid"}
   [:div {:class "row"}
    [:div {:class "col-md-2"}
     [search-component]
     [order-by-component]]
    [:div {:class "col-md-10"}
     [phones-component]]]])
