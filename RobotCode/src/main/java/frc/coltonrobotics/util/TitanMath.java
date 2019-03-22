package frc.electrotitans.util;
public class TitanMath{
    public class PositionState{
        private double xPos;
        private double yPos;
        private double radians;
        private double degrees;

        public PositionState(){

        }

        public PositionState(double x, double y){
            xPos = x;
            yPos = y;
        }

        public PositionState(double x, double y, double heading, boolean isRadians){
            xPos = x;
            yPos = y;
            radians = heading;
        }

        /**
         * @return the xPos
         */
        public double getX() {
            return xPos;
        }

        /**
         * @param xPos the xPos to set
         */
        public void setX(double xPos) {
            this.xPos = xPos;
        }

        /**
         * @return the yPos
         */
        public double getY() {
            return yPos;
        }

        /**
         * @param yPos the yPos to set
         */
        public void setY(double yPos) {
            this.yPos = yPos;
        }

        /**
         * @return the radians
         */
        public double getRadians() {
            return radians;
        }

        /**
         * @param radians the radians to set
         */
        public void setRadians(double radians) {
            this.radians = radians;
        }

        /**
         * @return the degrees
         */
        public double getDegrees() {
            return degrees;
        }

        /**
         * @param degrees the degrees to set
         */
        public void setDegrees(double degrees) {
            this.degrees = degrees;
        }

        
    }
}