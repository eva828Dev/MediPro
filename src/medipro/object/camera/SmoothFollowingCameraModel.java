package medipro.object.camera;

import medipro.object.base.gameobject.GameObjectModel;
import medipro.world.World;

/**
 * ターゲットをスムーズに追跡するカメラのモデル.
 */
public class SmoothFollowingCameraModel extends FollowingCameraModel {
    /**
     * ターゲットを追跡する際のスピード.
     */
    public Double followingSpeed = 0.1;

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
