(ns civic-game-jam.core
  (:require [play-cljs.core :as p]
            [goog.events :as events]))

(declare screen1)
(declare screen2)

(defonce game (p/create-game 500 500))
(defonce state (atom {:time-on-screen 22
                      :decision #{}}))

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
      (update-screen-time)
      (p/render game
                [[:fill {:color "green"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}
                  [:fill {:color "black"}
                    :text {:x 3 :y 15  :value "Coffee"}]]]
                 [:fill {:color "green"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}
                  [:fill {:color "black"}
                    :text {:x 3 :y 15 :color "black" :value "Tea"}]]]]))))

(def screen2
  (reify p/Screen
    (on-show [this] (reset-screen-time))
    (on-hide [this])
    (on-render [this]
      (update-screen-time)
      (p/render game
                [[:fill {:color "pink"}
                  [:rect {:x 0 :y 200 :width 200 :height 200}
                   :text {:x 3 :y 10 :color "black" :value "Coffee"}]]
                 [:fill {:color "pink"}
                  [:rect {:x 300 :y 200 :width 200 :height 200}
                   :text {:x 3 :y 10 :color "black" :value "Tea"}]]]))))

(defn decide [item]
  (swap! state :decision (conj (:decision state) item))

(defn check-click [event]
  (let [mouse-x (.-screenX event)
        mouse-y (.-screenY event)
        width (p/get-width game)
        screen-width (js->clj (.-innerWidth js/window))
        gme (p/get-screen game)]
    (js/console.log width)
    (js/console.log mouse-x)
    (js/console.log (:decision @state))
    (if (< mouse-x (/ screen-width 2))
      (cond ; left
        (= gme screen1) (decide :tea)
        (= gme screen2) (decide :apple))
      (cond ; right
        (= gme screen1) (decide :coffee))
        (= gme screen2) (decide :orange)))

(doto game
  (p/stop)
  (p/start)
  (p/set-screen screen1))

(events/listen js/window "mousedown"
               (fn [^js/MouseEvent event]
                 (change-screen)
                 (check-click event)))
