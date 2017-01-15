(ns mimic.board.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            ;[cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(println "This text is printed from src/mimic.board/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))



(defn dashboard []
  [:div
   [:h1 "Mimic Board"]
   [:div {:id "dashboard-container"}
    [:div#env.abs [:h2 "ENV"]]
    [:div#res.abs [:h2 "RESOURCE"]]
    [:div#rproxy.abs [:h2 "RPROXY"]]]])

(reagent/render-component [dashboard]
                          (. js/document (getElementById "dashboard-app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(let [div (. js/document (getElementById "dashboard-container"))]
  (. js/window runAutoLayout div (clj->js
                                   ["H:|-[header:[env]-[res]-[rproxy]]-|"
                                    "V:|[header]|"])))
