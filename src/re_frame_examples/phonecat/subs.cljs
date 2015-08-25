(ns re-frame-examples.phonecat.subs
    (:require [re-frame.core :as rf])
    (:require-macros [reagent.ratom  :refer [reaction]]))

(rf/register-sub
 :search-input
 (fn [db]
   (reaction (:search-input @db))))

(rf/register-sub        ;; a new subscription handler
 :phones             ;; usage (subscribe [:phones])
 (fn [db]
   (reaction (:phones @db))))  ;; pulls out :phones

(rf/register-sub
 :selected-image-url
 (fn
   ;; extract the selected-image-url from the db. If it's not set return the first image of the current phone under query
   [db [_ phone-id]]
   (let [phone (rf/subscribe [:phone-query phone-id])
         phone-details (rf/subscribe [:phone-details])
         images (reaction (:images @phone))]
     ;; Note how we are sequencing reactions above. Whenever the phone ratom changes the images ratom will change as well
     (reaction
      (if @phone-details
        (if-let [image-url (:selected-image-url @phone-details)]
          image-url
          (first @images)))))))

(rf/register-sub
 :order-prop
 (fn [db]
   (reaction (:order-prop @db))))

(rf/register-sub
 :phone-details
 (fn [db]
   (reaction (:phone-details @db))))

(rf/register-sub
 :phone-query
 (fn
   ;; get info on the given phone id from the phone-details map
   [db [_ phone-id]]
   (let [phone-details-reaction (reaction (:phone-details @db))]
     (reaction ((keyword phone-id) @phone-details-reaction)))))

