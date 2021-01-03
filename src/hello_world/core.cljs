(ns hello-world.core
  (:require [react-dom]
            [reagent.core :as r]
            [reagent.dom :as rd]))

(println "Hello world!")

(defonce click-count (r/atom 0))

(defonce all-data (r/atom ["The Gathering Storm"
                           "The Dragon Reborn"
                           "The Fellowship of the Ring"
                           "Six of Crows"]))

(defonce search-query (r/atom ""))

(defn average [a b]
  (/ (+ a b) 2.0))

(defn another-component []
  [:div
   [:p "This one does not do much"]])

(defn propped-component [stuff]
  [:div
   [:p "This one prints the " stuff " you tell it to"]])

(defn clicker []
  [:button {:on-click #(swap! click-count inc)}
   "I have been clicked " @click-count " times."])

(defn count-resetter []
  [:button {:on-click #(reset! click-count 0)}
   "Reset click count"])

(defn debug-thing []
  [:div
   [:p "Search query is " @search-query]
   [:p "Data is " (str @all-data)]])

(defn search-bar []
  [:div
   [:input {:on-input #(reset! search-query (.-value (.-target %1)))}]])

(defn search-results []
  (let [results (filter #(.includes % @search-query) @all-data)]
    [:div
     (into [:ul]
           (map (fn [item] [:li item]) results))]))

(defn some-component []
  [:div
   [:h3 "I am an h3"]
   [another-component]
   [propped-component "things!"]
   [clicker]
   [count-resetter]
   [debug-thing]
   [search-bar]
   [search-results]
   [:p.someclass
    "I have " [:strong "bold"] [:span {:style {:color "red"}} " and red"] " text."]])

(defn mountit []
  (rd/render [some-component]
             (.querySelector js/document "#app")))

(mountit)
