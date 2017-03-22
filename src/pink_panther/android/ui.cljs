(ns pink-panther.android.ui
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(def component-container-style {:border-radius 3
                                :border-color "#d6d7da"
                                :background-color "#ffffff"
                                :margin 10
                                :margin-vertical 5
                                :overflow "hidden"
                                :border-width 0.5})

(def title-bar-style {:border-bottom-width 0.5
                      :border-top-left-radius 3
                      :border-top-right-radius 2.5
                      :border-bottom-color "#d6d7da"
                      :background-color "#e6e7e8"
                      :padding-horizontal 10
                      :padding-vertical 5})

(defn todo-element [contents]
  [view {:style component-container-style}
   [view {:style title-bar-style}
    [text {:style {:font-size 16 :font-weight "500"}} contents]]])
