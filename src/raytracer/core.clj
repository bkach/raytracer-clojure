(ns raytracer.core)
(use 'clojure.math.combinatorics)
(use 'mikera.image.core)
(use 'mikera.image.colours)

(defn getColor [x y size]
  (if (< x (/ size 2))
    (rgb (/ x size) (/ y size) 1.0)
    (rgb 1.0 0 0)
  )
)

(defn setPixel [image x y size]
  (set-pixel image x y (getColor x y size)))

(defn setCoordsColors [image coords size]
  (let [coord (first coords)]
    (if (> (count coords) 0) 
      (do 
        (setPixel image (first coord) (second coord) size)
        (recur image (rest coords) size)
      )
    )
  )
)

(defn setPixelColors [image size]
  (let [numList (for [x (range 0 size)] x)
        coords (cartesian-product numList numList)]
    (setCoordsColors image coords size)
  )
)

(defn -main [] 
  (let [size 50
        image (new-image size size)]
    (setPixelColors image size)
    (show image :zoom 20.0 :title "We're not quite there yet")
  )
)
