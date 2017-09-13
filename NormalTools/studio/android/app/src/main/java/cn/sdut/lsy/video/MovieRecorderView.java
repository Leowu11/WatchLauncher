package cn.sdut.lsy.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.AudioSource;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OutputFormat;
import android.media.MediaRecorder.VideoSource;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fise.xiaoyu.R;
import com.fise.xiaoyu.utils.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 视频播放控件
 */           
@SuppressLint("NewApi")
public class MovieRecorderView extends LinearLayout implements OnErrorListener {

	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private ProgressBar mProgressBar;

	private MediaRecorder mMediaRecorder;
	private Camera mCamera;
//	private Timer mTimer;// 计时器
	private OnRecordFinishListener mOnRecordFinishListener;// 录制完成回调接口

	private int mWidth;// 视频分辨率宽度
	private int mHeight;// 视频分辨率高度
	private boolean isOpenCamera;// 是否一开始就打开摄像头
	private int mRecordMaxTime;// 一次拍摄最长时间
//	private int mTimeCount;// 时间计数
	private File mRecordFile = null;// 文件

	public MovieRecorderView(Context context) {
		this(context, null);
	}

	public MovieRecorderView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MovieRecorderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// 初始化各项组件
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.MovieRecorderView, defStyle, 0);
		mWidth = a.getInteger(R.styleable.MovieRecorderView_video_width, 320);// 默认320
		mHeight = a.getInteger(R.styleable.MovieRecorderView_video_height, 240);// 默认240

		isOpenCamera = a.getBoolean(
				R.styleable.MovieRecorderView_is_open_camera, true);// 默认打开
		mRecordMaxTime = a.getInteger(
				R.styleable.MovieRecorderView_record_max_time, 10);// 默认为10

		LayoutInflater.from(context)
				.inflate(R.layout.movie_recorder_view, this);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mProgressBar.setMax(mRecordMaxTime);// 设置进度条最大量

		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(new CustomCallBack());
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		a.recycle();
	}

	/**
	 */
	private class CustomCallBack implements Callback {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			if (!isOpenCamera)
				return;
			try {
				initCamera();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			if (!isOpenCamera)
				return;
			freeCameraResource();
		}

	}

	/**
	 * 初始化摄像头
	 * @throws IOException
	 */
	private void initCamera() throws IOException {
		if (mCamera != null) {
			freeCameraResource();
		}
		try {
			mCamera = Camera.open();
		} catch (Exception e) {
			e.printStackTrace();
			freeCameraResource();
		}
		if (mCamera == null)
			return;

		Camera.Parameters myParameters 	= mCamera.getParameters();
		List<Camera.Size> sizes = myParameters.getSupportedPreviewSizes();
		// setCameraParams();
		Log.i("aaa", "initCamera: "+mSurfaceView.getHeight()+" :　"+mSurfaceView.getWidth());
		Camera.Size optimalSize = getOptimalPreviewSize(sizes, mSurfaceView.getWidth(), mSurfaceView.getHeight()); //根据surfaceview控件的比例选择size
		myParameters.setPreviewSize(optimalSize.width, optimalSize.height); //进行size设置
		Log.i("aaa", "initCamera: "+optimalSize.width+" :　"+optimalSize.height);
		mCamera.setDisplayOrientation(90);
		mCamera.setPreviewDisplay(mSurfaceHolder);
		mCamera.startPreview();
		mCamera.unlock();
	}


	private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null) return null;

		Camera.Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;

		// Try to find an size match aspect ratio and size
		for (Camera.Size size : sizes) {
			double ratio = (double) size.width / size.height;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		// Cannot find the one match the aspect ratio, ignore the requirement
		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Camera.Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}
		return optimalSize;
	}
	/**
	 * 释放摄像头资源
	 */
	private void freeCameraResource() {
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.lock();
			mCamera.release();
			mCamera = null;
		}
	}

	private void createRecordDir() {
		File vecordDir = CommonUtil.getVedioSavePath();
		// 创建文件
		try {
			mRecordFile = File.createTempFile("recording", ".mp4", vecordDir); // mp4格式
//            mRecordFile = new File(sampleDir.getAbsolutePath() +"/recording.mp4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化
	 * @throws IOException
	 */
	private void initRecord() throws IOException {
		mMediaRecorder = new MediaRecorder();
		mMediaRecorder.reset();
		if (mCamera != null)
			mMediaRecorder.setCamera(mCamera);
		mMediaRecorder.setOnErrorListener(this);
		mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
		mMediaRecorder.setVideoSource(VideoSource.CAMERA);// 视频源
		mMediaRecorder.setAudioSource(AudioSource.MIC);// 音频源
		mMediaRecorder.setOutputFormat(OutputFormat.MPEG_4);// 视频输出格式
//		mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mMediaRecorder.setAudioEncoder(AudioEncoder.AMR_NB);// 音频格式
		mMediaRecorder.setVideoSize(mWidth, mHeight);// 设置分辨率：
		// mMediaRecorder.setVideoFrameRate(16);// 这个我把它去掉了，感觉没什么用
		mMediaRecorder.setVideoEncodingBitRate(1 * 1280 * 720);// 设置帧频率，然后就清晰了
		mMediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
		mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		// mediaRecorder.setMaxDuration(Constant.MAXVEDIOTIME * 1000);
		mMediaRecorder.setOutputFile(mRecordFile.getAbsolutePath());
		mMediaRecorder.prepare();
		try {
			mMediaRecorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开始录制视频
	 * @param
	 * 
	 * @param onRecordFinishListener
	 *            达到指定时间之后回调接口
	 */
	public void record(final OnRecordFinishListener onRecordFinishListener) {
		this.mOnRecordFinishListener = onRecordFinishListener;
		createRecordDir();
		try {
			if (!isOpenCamera)// 如果未打开摄像头，则打开
			{
				initCamera();
			}else{
				freeCameraResource();
				initCamera();
			}

			initRecord();
//			mTimeCount = 0;// 时间计数器重新赋值
//			mTimer = new Timer();
//			mTimer.schedule(new TimerTask() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					mTimeCount++;
//					mProgressBar.setProgress(mTimeCount);// 设置进度条
//					if (mTimeCount == mRecordMaxTime) {// 达到指定时间，停止拍摄
//						stop();
//						if (mOnRecordFinishListener != null)
//							mOnRecordFinishListener.onRecordFinish();
//					}
//				}
//			}, 0, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止拍摄
	 */
	public void stop() {
		stopRecord();
		releaseRecord();
		freeCameraResource();
	}

	/**
	 * 停止录制
	 */
	public void stopRecord() {
		mProgressBar.setProgress(0);
//		if (mTimer != null)
//			mTimer.cancel();
		if (mMediaRecorder != null) {
			// 设置后不会崩
			mMediaRecorder.setOnErrorListener(null);
			try {
				mMediaRecorder.stop();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mMediaRecorder.setPreviewDisplay(null);
		}
	}

	/**
	 * 释放资源
	 */
	private void releaseRecord() {
		if (mMediaRecorder != null) {
			mMediaRecorder.setOnErrorListener(null);
			try {
				mMediaRecorder.release();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mMediaRecorder = null;
	}

//	public int getTimeCount() {
////		return mTimeCount;
//	}

	/**
	 * @return the mVecordFile
	 */
	public File getmRecordFile() {
		return mRecordFile;
	}

	/**
	 * 录制完成回调接口
	 */
	public interface OnRecordFinishListener {
		public void onRecordFinish();
	}

	@Override
	public void onError(MediaRecorder mr, int what, int extra) {
		try {
			if (mr != null)
				mr.reset();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}