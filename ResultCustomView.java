package co.chatsdk.android.app.myapplication;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ResultCustomView extends View {

	int PANDDING = 0;
	int WINNER = 2;
	int LoSSER = 1;
	int layout_width;
	int layout_hight;
	int imageCenterY;
	int userCircalX;
	int challangeCircalX;
	int winnerBorder;
	int losserBordder;

	int status = LoSSER;

	int textBoxwidth;
	int textBoxHight;
	int winnerRadius;
	int losserRadius;

	int winnerTextBoxoffset;
	int losserTextBoxoffset;

	int userCircalRadius;
	int challageCircalRadius;

	int userCircalBorder;
	int challangeCircalBorder;

	int winnerBorderColor = Color.MAGENTA;
	int losserBorderColor = Color.LTGRAY;

	int userBorderColor;
	int challangeBorderColor;

	int textColor = Color.BLACK;

	int userTextBoxoffSet;
	int challangerTextBoxoffSet;

	int userImage_id = R.drawable.default_face_icon;
	int challangerImage_id = R.drawable.default_face_icon;

	int textSize = 30;
	Bitmap userImageBitmap;
	Bitmap challangerBitmap;

	String userImageUrl = "http://s9.postimg.org/n92phj9tr/image1.jpg";
	String callangerImageUrl = "http://s9.postimg.org/n92phj9tr/image1.jpg";

	String userName = "nitish";
	String challangerName = "vipin";

	Paint borderPaint = new Paint();
	Paint textPaint = new Paint();

	public ResultCustomView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		losserBorderColor = context.getResources().getColor(
				R.color.orange);
		winnerBorderColor = context.getResources().getColor(
				R.color.SearchLayOutBackGround);
        parseAttributes(context.obtainStyledAttributes(attrs,
                R.styleable.CustomView));
    }

    private void parseAttributes(TypedArray obtainStyledAttributes) {

        textSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CustomView_text_size,
                textSize);
	}

	public ResultCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		losserBorderColor = context.getResources().getColor(
				R.color.orange);
		winnerBorderColor = context.getResources().getColor(
				R.color.SearchLayOutBackGround);
			parseAttributes(context.obtainStyledAttributes(attrs,
					R.styleable.CustomView));

	}

	public ResultCustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		losserBorderColor = context.getResources().getColor(
				R.color.orange);
		winnerBorderColor = context.getResources().getColor(
				R.color.SearchLayOutBackGround);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		calculation();
		borderPaint.setStyle(Style.FILL_AND_STROKE);
		borderPaint.setColor(userBorderColor);
		textPaint.setColor(textColor);
		textPaint.setStyle(Style.STROKE);
		textPaint.setTextSize(textSize);
		canvas.drawCircle(userCircalX, imageCenterY, userCircalRadius,
				borderPaint);

		float textHight = textPaint.descent() - textPaint.ascent();
		float vaticaloffSet = (textHight / 2) - textPaint.descent();
		float hoigentelOffSet = textPaint.measureText(userName) / 2;
		float textY = (imageCenterY + userCircalRadius + (userTextBoxoffSet) + textBoxHight / 2)
				- vaticaloffSet / 2;
		float textX = userCircalX - hoigentelOffSet;
		canvas.drawText(userName, textX, textY, textPaint);
		borderPaint.setColor(challangeBorderColor);

		hoigentelOffSet = textPaint.measureText(challangerName) / 2;
		textY = (imageCenterY + challageCircalRadius
				+ (challangerTextBoxoffSet) + textBoxHight / 2)
				- vaticaloffSet / 2;
		textX = challangeCircalX - hoigentelOffSet;
		canvas.drawText(challangerName, textX, textY, textPaint);

		canvas.drawCircle(challangeCircalX, imageCenterY, challageCircalRadius,
				borderPaint);

		userImageBitmap = getUserBitmap();

		int imageLeft = (userCircalX - userCircalRadius) + userCircalBorder;
		int imageTop = (imageCenterY - userCircalRadius) + userCircalBorder;

		canvas.drawBitmap(userImageBitmap, imageLeft, imageTop, null);

		challangerBitmap = getChallangerBitMap();

		imageLeft = (challangeCircalX - challageCircalRadius)
				+ challangeCircalBorder;
		imageTop = (imageCenterY - challageCircalRadius)
				+ challangeCircalBorder;

		canvas.drawBitmap(challangerBitmap, imageLeft, imageTop, null);

		super.onDraw(canvas);
	}

	private void calculation() {
		layout_hight= getMeasuredHeight()-(getPaddingTop() + getPaddingBottom());

		layout_width = getMeasuredWidth()
				- (getPaddingLeft() + getPaddingRight());
		/*if(layout_hight<layout_width){
			layout_width=layout_hight;
		}
			else{
			layout_hight=layout_width;
		}*/
		winnerRadius = (layout_width * 11) / 48;
		losserRadius = (layout_width * 13) / 96;
		winnerBorder = (layout_width * 2) / 48;
		losserBordder = layout_width / 48;

		/*layout_hight = (winnerRadius * 2) + ((layout_width * 5) / 48);
		*/setMeasuredDimension(layout_width + getPaddingLeft()
				+ getPaddingRight(), layout_hight + getPaddingTop()
				+ getPaddingBottom());
		textBoxHight = (layout_width * 4) / 48;
		imageCenterY = winnerRadius;
		userCircalX = (layout_width * 150) / 480;
		challangeCircalX = (layout_width * 350) / 480;
		winnerBorder = (layout_width * 2) / 48;
		losserBordder = layout_width / 48;
		winnerTextBoxoffset = (layout_width * 3) / 48;
		losserTextBoxoffset = (layout_width * 2) / 48;
		if (status == PANDDING) {
			winnerRadius = losserRadius;
			winnerBorder = losserBordder;
		}

		if (status == WINNER)

		{
			userCircalRadius = winnerRadius;
			userCircalBorder = winnerBorder;
			challangeCircalBorder = losserBordder;
			challageCircalRadius = losserRadius;
			userBorderColor = winnerBorderColor;
			challangeBorderColor = losserBorderColor;
			userTextBoxoffSet = winnerTextBoxoffset;
			challangerTextBoxoffSet = losserTextBoxoffset;

		} else if (status == LoSSER) {
			userCircalBorder = losserBordder;
			challangeCircalBorder = winnerBorder;
			userCircalRadius = losserRadius;

			challageCircalRadius = winnerRadius;

			userBorderColor = losserBorderColor;
			challangeBorderColor = winnerBorderColor;

			userTextBoxoffSet = losserTextBoxoffset;
			challangerTextBoxoffSet = winnerTextBoxoffset;

		} else {
			userCircalBorder = losserBordder;
			challangeCircalBorder = losserBordder;
			userCircalRadius = losserRadius;
			challageCircalRadius = losserRadius;
			userBorderColor = losserBorderColor;
			challangeBorderColor = losserBorderColor;
			userTextBoxoffSet = losserTextBoxoffset;
			challangerTextBoxoffSet = losserTextBoxoffset;
		}

	}

	private Bitmap getImageFromId(int id, int radius) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);

		bitmap = Utilty.getRoundedCroppedBitmap(bitmap, radius);

		return bitmap;
	}

	public Bitmap getUserBitmap() {
		Bitmap rewBitMap = null;
		int radius = (userCircalRadius * 2) - (userCircalBorder * 2);

		if (userImageBitmap != null) {
			rewBitMap = Utilty.getRoundedCroppedBitmap(userImageBitmap, radius);
		} else if (!(userImageUrl.equals("null"))) {
			rewBitMap = getImageFromId(userImage_id, radius);
			new Utilty().getImageFromUrl(this, userImageBitmap, userImageUrl,
					radius);
		} else {
			rewBitMap = getImageFromId(userImage_id, radius);
		}

		return rewBitMap;
	}

	public Bitmap getChallangerBitMap() {
		int radius = (challageCircalRadius * 2) - (challangeCircalBorder * 2);
		Bitmap rewBitMap = null;
		if (challangerBitmap != null) {
			rewBitMap = Utilty
					.getRoundedCroppedBitmap(challangerBitmap, radius);
		} else if (!(callangerImageUrl.equals("null"))) {
			rewBitMap = getImageFromId(challangerImage_id, radius);
			new Utilty().getImageFromUrl(this, challangerBitmap,
					callangerImageUrl, radius);
		} else {
			rewBitMap = getImageFromId(challangerImage_id, radius);
		}

		return rewBitMap;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		invalidate();
	}

	public int getUserBorderColor() {
		return userBorderColor;
	}

	public void setUserBorderColor(int userBorderColor) {
		this.userBorderColor = userBorderColor;
		invalidate();
	}

	public int getChallangeBorderColor() {
		return challangeBorderColor;
	}

	public void setChallangeBorderColor(int challangeBorderColor) {
		this.challangeBorderColor = challangeBorderColor;
		invalidate();
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
		invalidate();
	}

	public int getUserImage_id() {
		return userImage_id;
	}

	public void setUserImage_id(int userImage_id) {
		this.userImage_id = userImage_id;
		invalidate();
	}

	public int getChallangerImage_id() {
		return challangerImage_id;
	}

	public void setChallangerImage_id(int challangerImage_id) {
		this.challangerImage_id = challangerImage_id;
		invalidate();
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		invalidate();
	}

	public Bitmap getUserImageBitmap() {
		return userImageBitmap;
	}

	public void setUserImageBitmap(Bitmap userImageBitmap) {
		this.userImageBitmap = userImageBitmap;
		invalidate();
	}

	public Bitmap getChallangerBitmap() {
		return challangerBitmap;
	}

	public void setChallangerBitmap(Bitmap challangerBitmap) {
		this.challangerBitmap = challangerBitmap;
		invalidate();
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
		invalidate();
	}

	public String getCallangerImageUrl() {
		return callangerImageUrl;

	}

	public void setCallangerImageUrl(String callangerImageUrl) {
		this.callangerImageUrl = callangerImageUrl;
		invalidate();
	}

	public String getUserName() {
		return userName;

	}

	public void setUserName(String userName) {
		this.userName = userName;
		invalidate();
	}

	public String getChallangerName() {
		return challangerName;
	}

	public void setChallangerName(String challangerName) {
		this.challangerName = challangerName;
		invalidate();
	}

}
