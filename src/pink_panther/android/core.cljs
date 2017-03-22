(ns pink-panther.android.core
  (:require [reagent.core :as r]
            [pink-panther.android.ui :as ui]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [pink-panther.events]
            [pink-panther.subs]))

(def logo-img (js/require "./images/cljs.png"))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [ui/view
       [ui/todo-element "WOW"]
       [ui/todo-element "WOW"]
       [ui/todo-element "WOW"]])))
      ; [ui/list-view
      ;  [ui/text "FOo Bar"]])))
      ; [ui/view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
      ;  [ui/text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
      ;  [ui/image {:source logo-img
      ;             :style  {:width 80 :height 80 :margin-bottom 30}}]
      ;  [ui/touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
      ;                           :on-press #(ui/alert "HELLO!")}
      ;   [ui/text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]])))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent ui/app-registry "PinkPanther" #(r/reactify-component app-root)))
