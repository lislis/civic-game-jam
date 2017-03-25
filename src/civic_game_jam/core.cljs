(ns civic-game-jam.core
  (:require [play-cljs.core :as p]
            [goog.events :as events]))

(declare screen1 n-screen1 n-screen2 screen2 n-screen3 screen3 n-screen4 screen4
         n-screen5 screen5 n-screen6 screen6 n-screen7 screen7 n-screen8 screen8
         screen-title)

(defonce game (p/create-game 1280 800))
(defonce state (atom {:time-on-screen 22
                      :decision #{}
                      :img1 (p/load-image game "images/1.png")
                      :img2 (p/load-image game "images/2.png")
                      :img3 (p/load-image game "images/3.png")
                      :img4 (p/load-image game "images/4.png")
                      :img5 (p/load-image game "images/5.png")
                      :img6 (p/load-image game "images/6.png")
                      :img7 (p/load-image game "images/7.png")
                      :img8 (p/load-image game "images/8.png")
                      :img-intro (p/load-image game "images/title.png")
                      :img-pause (p/load-image game "images/pause.png")
                      }))

(def max-screen-time 2000)

(defn reset-screen-time []
  (swap! state assoc :time-on-screen 0))

(defn change-screen []
  (let [gme (p/get-screen game)]
    (cond
      (= gme n-screen1) (p/set-screen game screen1)
      (= gme screen1) (p/set-screen game n-screen2)
      (= gme n-screen2) (p/set-screen game screen2)
      (= gme screen2) (p/set-screen game n-screen3)
      (= gme n-screen3) (p/set-screen game screen3)
      (= gme screen3) (p/set-screen game n-screen4)
      (= gme n-screen4) (p/set-screen game screen4)
      (= gme screen4) (p/set-screen game n-screen5)
      (= gme n-screen5) (p/set-screen game screen5)
      (= gme screen5) (p/set-screen game n-screen6)
      (= gme n-screen6) (p/set-screen game screen6)
      (= gme screen6) (p/set-screen game n-screen7)
      (= gme n-screen7) (p/set-screen game screen7)
      (= gme screen7) (p/set-screen game n-screen8))))

(defn update-screen-time [max]
  (if (< (:time-on-screen @state) max)
    (swap! state assoc :time-on-screen (+ (:time-on-screen @state) (p/get-delta-time game)))
    (change-screen)))

(def n-screen1
  ;; good morning
  (reify p/Screen
    (on-show [this] (js/sound1.play))
    (on-hide [this] (js/sound1.stop))
    (on-render [this]
      (update-screen-time 26000)
      (p/render game [:image {:value (:img-intro @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen1
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img1 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen2
 (reify p/Screen
    (on-show [this] (js/sound2.play))
    (on-hide [this] (js/sound2.stop))
    (on-render [this]
      (update-screen-time 18000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen2
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img2 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen3
  (reify p/Screen
    (on-show [this] (js/sound3.play))
    (on-hide [this] (js/sound3.stop))
    (on-render [this]
      (update-screen-time 19000)
      (js/clock.play)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen3
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img3 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen4
  (reify p/Screen
    (on-show [this] (js/sound4.play))
    (on-hide [this] (js/sound4.stop))
    (on-render [this]
      (update-screen-time 10000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen4
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img4 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen5
  (reify p/Screen
    (on-show [this] (js/sound5.play))
    (on-hide [this] (js/sound5.stop))
    (on-render [this]
      (update-screen-time 5000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen5
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img5 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen6
  (reify p/Screen
    (on-show [this] (js/sound6.play))
    (on-hide [this] (js/sound6.stop))
    (on-render [this]
      (update-screen-time 4000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen6
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img6 @state) :x 0 :y 0 :width 1280 :height 800}]))))


(def n-screen7
  (reify p/Screen
    (on-show [this] (js/sound7.play))
    (on-hide [this] (js/sound7.stop))
    (on-render [this]
      (update-screen-time 5000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen7
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img7 @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def n-screen8
  (reify p/Screen
    (on-show [this] (js/sound8.play))
    (on-hide [this] (js/sound8.stop))
    (on-render [this]
      (update-screen-time 12000)
      (p/render game [:image {:value (:img-pause @state) :x 0 :y 0 :width 1280 :height 800}]))))

(def screen8
  (reify p/Screen
    (on-show [this] (do
                      (reset-screen-time)
                      (js/clock.play)))
    (on-hide [this] (js/clock.stop))
    (on-render [this]
      (update-screen-time max-screen-time)
      (p/render game [:image {:value (:img8 @state) :x 0 :y 0 :width 1280 :height 800}]))))


(defn decide [item]
  (swap! state assoc :decision (conj (:decision @state) item)))

(defn check-click [event]
  (let [mouse-x (.-screenX event)
        mouse-y (.-screenY event)
        width (p/get-width game)
        screen-width (js->clj (.-innerWidth js/window))
        gme (p/get-screen game)]
    (js/console.log width)
    (js/console.log mouse-x)
    (js/console.log (str (:decision @state)))
    (if (< mouse-x (/ screen-width 2))
      (cond ; left
        (= gme screen1) (decide :tea)
        (= gme screen2) (decide :apple))
      (cond ; right
        (= gme screen1) (decide :coffee)
        (= gme screen2) (decide :orange)))))

(doto game
  (p/stop)
  (p/start)
  (p/set-screen n-screen1))

(events/listen js/window "mousedown"
               (fn [^js/MouseEvent event]
                 (change-screen)
                 (check-click event)))
