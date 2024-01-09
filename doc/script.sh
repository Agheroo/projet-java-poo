for f in *dot; do dot "$f" -tpng -o "$(basename "$f" .dot)".png; done

