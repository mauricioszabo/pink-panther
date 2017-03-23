(ns pink-panther.android.ui-helper
  (:require [clojure.string :as str]))

(defmacro register [element]
  (let [js-elem (symbol (str ".-" element))
        clj-elem (symbol (-> element
                             (str/replace #"([A-Z])" "-$1")
                             (str/replace-first #"-" "")
                             str/lower-case))]
    `(def ~clj-elem
       (reagent.core/adapt-react-class
        (~js-elem pink-panther.android.ui/ReactNative)))))
