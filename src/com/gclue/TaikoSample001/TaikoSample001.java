package com.gclue.TaikoSample001;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TaikoSample001 extends Activity {

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		// �`��N���X�̃C���X�^���X�𐶐�
		MyView mView = new MyView( this );
		setContentView( mView );
	}
}

class MyView extends View {
	/** ���ۉ摜�f�[�^��ێ�����B */
	private Bitmap taikoImage;
	/** ���ۉ摜�̌��_�i����j��x���W��ێ�����B */
	private int taikoX = 100;
	/** ���ۉ摜�̌��_�i����j��y���W��ێ�����B */
	private int taikoY = 50;
	/** �T�E���h�Đ��f�[�^��ێ�����B */
	private MediaPlayer mp;

	/**
	 * �R���X�g���N�^�B
	 * @param context �R���e�L�X�g
	 */
	public MyView( Context context ) {
		super( context );

		// �C�x���g�擾�ł���悤��Focus��L���ɂ���
		setFocusable( true );
		// Resource�C���X�^���X�̐���
		Resources res = this.getContext().getResources();
		// �摜�̓ǂݍ��݁i/res/drawable-hdpi/taiko.png�j
		taikoImage = BitmapFactory.decodeResource( res, R.drawable.taiko );

		// �T�E���h�f�[�^��ǂݍ���(/res/raw/pon.mp3)
		mp = MediaPlayer.create( context, R.raw.pon );
	}

	/**
	 * �`�揈���B
	 */
	@Override
	protected void onDraw( Canvas canvas ) {
		// �w�i�F��ݒ肷��
		canvas.drawColor( Color.BLACK );

		// Bitmap�C���[�W�̕`��
		Paint mPaint = new Paint();
		canvas.drawBitmap( taikoImage, taikoX, taikoY, mPaint );
	}

	/**
	 * �^�b�`�C�x���g
	 */
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			// �w���^�b�`���ꂽx,y���W�̎擾
			int touchX = (int) event.getX();
			int touchY = (int) event.getY();

			// ���ۂ̒��S���W���v�Z
			int centerX = taikoX + taikoImage.getWidth() / 2;
			int centerY = taikoY + taikoImage.getHeight() / 2;

			// ���ۂ̒��S���W�Ǝw�̋������v�Z
			double distance = Math.sqrt(
				Math.pow( (centerX - touchX), 2 )
					+ Math.pow( (centerY - touchY), 2 )
				);

			// ���ۉ摜�̔��a
			int taikoR = taikoImage.getWidth() / 2;

			// �����蔻��
			if ( distance < taikoR ) {
				// �T�E���h�Đ�
				mp.start();
			}
		}
		return true;
	}
}