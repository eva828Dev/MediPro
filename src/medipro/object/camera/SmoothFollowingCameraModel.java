package medipro.object.camera;

import medipro.object.base.World;
import medipro.object.base.gameobject.GameObjectModel;

/**
 * ターゲットをスムーズに追跡するカメラのモデル.
 */
public class SmoothFollowingCameraModel extends FollowingCameraModel {
    /**
     * ターゲットを追跡する際のスピード. 最大スピードに対する割合で指定する.
     */
    public Double followingSpeed = 0.1;

    private boolean isFlipOriginXWithPlayerDirection = true;

    public boolean isFlipOriginXWithPlayerDirection() {
        return isFlipOriginXWithPlayerDirection;
    }

    public void setFlipOriginXWithPlayerDirection(boolean isFlipOriginXWithPlayerDirection) {
        this.isFlipOriginXWithPlayerDirection = isFlipOriginXWithPlayerDirection;
    }

    private double flipSpeed = 50;

    public double getFlipSpeed() {
        return flipSpeed;
    }

    public void setFlipSpeed(double flipSpeed) {
        this.flipSpeed = flipSpeed;
    }

    /**
     * カメラモデルを生成する.
     * 
     * @param world  モデルが存在するワールド
     * @param target 追跡する対象のオブジェクト
     */
    public SmoothFollowingCameraModel(World world, GameObjectModel target) {
        super(world, target);
    }
}
