(ns pink-panther.android.core
  (:require [reagent.core :as r]
            [pink-panther.android.ui :as ui]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [pink-panther.events]
            [pink-panther.subs]))

(def logo-img (js/require "./images/cljs.png"))

(defonce state (r/atom {:tasks [{:value "One" :done? true}
                                {:value "Two"}
                                {:value "Three"}]}))

(defn remove-item [id]
  (swap! state update :tasks (fn [tasks] (->> tasks
                                              (remove #(= (:id %) id))
                                              vec))))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [ui/view {:style {:margin 5}}
       [ui/scroll-view
        (->> @state :tasks count
             range
             (map (fn [i] ^{:key i} [ui/todo-element (r/cursor state [:tasks i]) remove-item])))
        [ui/touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                                 :on-press #(swap! state update :tasks conj {:value "New Task"})}
         [ui/text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]]])))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent ui/app-registry "PinkPanther" #(r/reactify-component app-root)))
