package com.ligr.strategygame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import constants.ConstantBuildings;

public class Fountain extends SpriteObject {

	private boolean pressedDown = false;

	public Fountain(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, boolean load) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		if (!load)
			MainActivity.updateWorkers(ConstantBuildings.WORKERSFOUNTAIN, 0);

	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionDown()) {
			pressedDown = true;
		}
		if (pSceneTouchEvent.isActionUp()) {

			if (MainActivity.boolplacebuilding == false && pressedDown
					&& MainActivity.removeBuildings == false) {
				pressedDown = false;
				MainActivity.addBuildingDescription();
				MainActivity.buildingDescriptionTitleString = "Fountain";
				MainActivity.buildingDescriptionTitle
						.setText(MainActivity.buildingDescriptionTitleString);
				MainActivity.buildingDescriptionDetailString = "The fountain provides nearby houses with a steady supply of water";
				MainActivity.buildingDescriptionDetail
						.setText(MainActivity.buildingDescriptionDetailString);
			} else if (MainActivity.removeBuildings == true
					&& MainActivity.boolplacebuilding == false && pressedDown) {
				MainActivity.MessagePopUpChoice(
						"Are you sure you want to remove the building?",
						Color.WHITE, this, "Remove", MainActivity.inGameHUD);
			}

		}
		return true;
	}

	public void removeEntity() {
		MainActivity.Fountains.remove(this);
		MainActivity.mScene.unregisterTouchArea(this);
		MainActivity.removeEntity(this);
		MainActivity.updateWorkers(-ConstantBuildings.WORKERSFOUNTAIN, 0);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

	}

	public void save() {
		MainActivity.dataBase.add(name(), this.getX(), this.getY(), this.id);
	}

	private String name() {
		return "Fountain";
	}
}
