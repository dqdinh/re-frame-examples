(ns re-frame-examples.phonecat.views.phone-details
    (:require [reagent.core :as r]
              [re-frame.core :as rf])
    (:require-macros [reagent.ratom  :refer [reaction]]))

(defn phone-info-template
  "template for listing a set of phone attributes"
  [section-title attributes-map]
  [:li
   [:span section-title]
   [:dl
    (map (fn [attribute]
           ^{:key (:name attribute)} [:div
                              [:dt (:name attribute)]
                              [:dd (condp = (:value attribute)
                                     true "\u2713"
                                     false "\u2718"
                                     (:value attribute))]])
         attributes-map)]])

(defn thumbnails
  "component for displaying thumbnails of the phone"
  [phone]
  [:ul {:class "phone-thumbs"}
   (for [image (:images @phone)]
     ^{:key image} [:li [:img {:src image
                               :class "phone"
                               :on-click #(rf/dispatch [:set-image image])}]])])

(defn availability
  [availability]
  [:li
   [:span "Availability and Networks"]
   [:dl
    [:dt "Availability"]
    (for [availability @availability]
      availability)]])

(defn battery
  [battery]
  [phone-info-template "Battery" [{:name "Type"
                                   :value (:type @battery)}
                                  {:name "Talk Time"
                                   :value (:talkTime @battery)}
                                  {:name "Standby time (max)"
                                   :value (:standbyTime @battery)}]])

(defn storage-and-memory
  [storage]
  [phone-info-template "Storage And Memory"  [{:name "RAM"
                                               :value (:ram @storage)}
                                              {:name "Internal Storage"
                                               :value (:flash @storage)}]])

(defn connectivity
  [connectivity]
  [phone-info-template "Connectivity" [{:name "Network Support"
                                        :value (:cell @connectivity)}
                                       {:name "Wifi"
                                        :value (:wifi @connectivity)}
                                       {:name "Bluetooth"
                                        :value (:bluetooth @connectivity)}]])

(defn android
  [android]
  [phone-info-template "Android" [{:name "OS Version"
                                   :value (:os @android)}
                                  {:name "UI"
                                   :value (:ui @android)}]])

(defn size-and-weight
  [size-and-weight]
  [phone-info-template "Size And Weight" [{:name "Dimensions"
                                           :value (:dimensions @size-and-weight)}
                                          {:name "Weight"
                                           :value (:weight @size-and-weight)}]])

(defn display
  [display]
  [phone-info-template "Display" [{:name "Screen size"
                                   :value (:screenSize @display)}
                                  {:name "Screen resolution"
                                   :value (:screenResolution @display)}
                                  {:name "Touch screen"
                                   :value (:touchScreen @display)}]])

(defn hardware
  [hardware]
  [phone-info-template "Hardware" [{:name "CPU"
                                    :value (:cpu @hardware)}
                                   {:name "USB"
                                    :value (:usb @hardware)}
                                   {:name "Audio / headphone jack"
                                    :value (:audioJack @hardware)}
                                   {:name "FM Radio"
                                    :value (:fmRadio @hardware)}
                                   {:name "Accelerometer"
                                    :value (:accelerometer @hardware)}]])

(defn camera
  [camera]
  [phone-info-template "Camera" [{:name "Primary"
                                  :value (:primary @camera)}
                                 {:name "Features"
                                  :value (clojure.string/join ", " (:features @camera))}]])

(defn additional-features
  [additional-features]
  [:li
   [:span "Additional Features"]
   [:dd @additional-features]])

;; TODO move to subs
(defn specs
  "component for displaying the specs of the phone"
  [phone]
  [:ul {:class "specs"}
   [availability (reaction (:availiability @phone))]
   [battery (reaction (:battery @phone))]
   [storage-and-memory (reaction (:storage @phone))]
   [connectivity (reaction (:connectivity @phone))]
   [android (reaction (:android @phone))]
   [display (reaction (:display @phone))]
   [hardware (reaction (:hardware @phone))]
   [camera (reaction (:camera @phone))]
   [additional-features (reaction (:additionalFeatures @phone))]])

(defn phone-page
  "top level component for the phone page"
  [phone-id]
  (let [phone (rf/subscribe [:phone-query phone-id])
        image-url (rf/subscribe [:selected-image-url phone-id])]
    (fn []
      [:div
       [:img {:src @image-url
              :class "phone"}]
       [:h1 (:name @phone)]
       [:p (:description @phone)]
       [thumbnails phone]
       [specs phone]])))
