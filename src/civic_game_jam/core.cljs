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
                       :next :screen2}
             :screen2 {
                       :text "This is the 2nd screen"
                       :left "left2"
                       :right "coffee"
                       :next :screen1}})

(defonce state (atom {:time-on-screen 22}))

(def max-screen-time 2000)

(defn reset-screen-time []
  (swap! state assoc :time-on-screen 0))

(defn change-screen []
  (let [gme (p/get-screen game)]
    (cond
      (= gme screen1) (p/set-screen game screen2)
      (= gme screen2) (p/set-screen game screen1))))

(defn update-screen-time []
  (if (< (:time-on-screen @state) max-screen-time)
    (swap! state assoc :time-on-screen (+ (:time-on-screen @state) (p/get-delta-time game)))
    (change-screen)))

(def screen1
  (reify p/Screen
    (on-show [this] (reset-screen-time))
    (on-hide [this])
    (on-render [this]
      (js/console.log (:time-on-screen @state))
      (update-screen-time)
      (p/render game
                [[:fill {:color "green"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}]]
                 [:fill {:color "blue"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}]]]))))

(def screen2
  (reify p/Screen
    (on-show [this] (reset-screen-time))
    (on-hide [this])
    (on-render [this]
      (update-screen-time)
      (p/render game
                [[:fill {:color "pink"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}]]
                 [:fill {:color "orange"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}]]]))))



(defn check-click [event]
  (let [current-state (:current @state)
        next-screen (:next (current-state design))]
    ;(p/set-screen game (get screens next-screen))
    (js/console.log current-state)
    ;
    ))

(doto game
  (p/stop)
  (p/start)
  (p/set-screen screen1))

(events/listen js/window "mousedown"
               (fn [^js/MouseEvent event]
                 (change-screen)
                 ; (check-click event))
                 ))
