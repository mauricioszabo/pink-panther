(ns pink-panther.android.ui
  (:require-macros [pink-panther.android.ui-helper :as ui])
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(ui/register Text)
(ui/register Button)
(ui/register TextInput)
(ui/register View)
(ui/register ScrollView)
(ui/register ListView)
(ui/register Image)
(ui/register TouchableHighlight)

(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(def component-container-style {:border-radius 3
                                :border-color "#d6d7da"
                                :background-color "#ffffff"
                                :margin-bottom 10
                                ; :margin-vertical 5
                                :overflow "hidden"
                                :border-width 0.5})

(def title-bar-style {:border-bottom-width 0.5
                      :border-top-left-radius 3
                      :border-top-right-radius 2.5
                      :border-bottom-color "#d6d7da"
                      :background-color "#e6e7e8"
                      :padding-horizontal 10
                      :padding-vertical 5})

(def todo-text-style {:font-size 16
                      :font-weight "500"
                      :padding-bottom 5
                      :flex 1})

(defn todo-element [cursor]
  [view {:style component-container-style}
   [view {:style title-bar-style}
    [view {:style {:flex-direction "row"}}
     (if (:done? @cursor)
       [text {:style (assoc todo-text-style :text-decoration-line "line-through")}
        (:value @cursor)]
       [view {:flex-direction "row" :flex 1}
        [touchable-highlight {:on-press #(swap! cursor assoc :done? true)}
         [text  "Mark as\nDone"]]
        [text-input {:style todo-text-style
                     :underline-color-android "transparent"
                     :on-change-text #(swap! cursor assoc :value %)}
         (:value @cursor)]])
     [touchable-highlight {:on-press #()} [text  "Delete"]]]]])
