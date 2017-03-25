(ns civic-game-jam.core
  (:require [play-cljs.core :as p]
            [goog.events :as events]))

(declare screen1)
(declare screen2)


(defonce game (p/create-game 500 500))
(def design {
             :screen1 {
                       :text "This is screen1 choose left or right"
                       :left "left"
                       :right "right"
                       :next screen2}
             :screen2 {
                       :text "This is the 2nd screen"
                       :left "left2"
                       :right "coffee"
                       :next screen1}})

(defonce state (atom {
                      :current screen1}))

(defn check-click [event]

  (let [next-screen (:next ((:current @state) design))]
    (p/set-screen game next-screen)
    (js/console.log next-screen)
    (swap! state update :current next-screen))
  )

(def main-screen
  (reify p/Screen
    (on-show [this]
      (reset! state {:text-x 20 :text-y 30}))
    (on-hide [this])
    (on-render [this]
      (p/render game
        [[:fill {:color "lightblue"}
          [:rect {:x 0 :y 0 :width 500 :height 500}]]
         [:fill {:color "black"}
          [:text {:value "Hello, world!" :x (:text-x @state) :y (:text-y @state) :size 16 :font "Georgia" :style :italic}]]])
      (swap! state update :text-x inc))))

(def screen1
  (reify p/Screen
    (on-show [this] ())
    (on-hide [this])
    (on-render [this]
      (p/render game
                [[:fill {:color "green"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}]]
                 [:fill {:color "blue"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}]]]))))

(def screen2
  (reify p/Screen
    (on-show [this] ())
    (on-hide [this])
    (on-render [this]
      (p/render game
                [[:fill {:color "pink"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}]]
                 [:fill {:color "orange"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}]]]))))

(doto game
  (p/stop)
  (p/start)
  (p/set-screen screen1))


(events/listen js/window "mousedown"
               (fn [^js/MouseEvent event]
                 (check-click event)))
