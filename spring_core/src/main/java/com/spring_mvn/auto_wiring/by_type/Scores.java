package com.spring_mvn.auto_wiring.by_type;

public class Scores {
    private float physics;
    private float chemistry;
    private float maths;

    public Scores() {
    }

    public Scores(float physics, float chemistry, float maths) {
        this.physics = physics;
        this.chemistry = chemistry;
        this.maths = maths;
    }

    public float getPhysics() {
        return physics;
    }

    public void setPhysics(float physics) {
        this.physics = physics;
    }

    public float getChemistry() {
        return chemistry;
    }

    public void setChemistry(float chemistry) {
        this.chemistry = chemistry;
    }

    public float getMaths() {
        return maths;
    }

    public void setMaths(float maths) {
        this.maths = maths;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "physics=" + physics +
                ", chemistry=" + chemistry +
                ", maths=" + maths +
                '}';
    }
}
