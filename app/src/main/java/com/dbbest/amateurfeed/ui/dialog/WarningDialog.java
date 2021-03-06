package com.dbbest.amateurfeed.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbbest.amateurfeed.R;

/**
 * Created by antonina on 20.01.17.
 */

public class WarningDialog extends BaseDialogFragment implements View.OnClickListener {
    private static final int NO_VALUE = -1;


    private static final String KEY_OK_TEXT = "OK_TEXT";
    private static final String KEY_CANCEL_TEXT = "CANCEL_TEXT";
    private static final String KEY_MESSAGE_TEXT = "MESSAGE_TEXT";
    private static final String KEY_MESSAGE_TEXT_STR = "MESSAGE_TEXT_STR";

    private static final String KEY_CANCELABLE = "CANCELABLE";

    private static final String KEY_OK_COLOR = "OK_COLOR";
    private static final String KEY_CANCEL_COLOR = "CANCEL_COLOR";
    private static final String KEY_MESSAGE_COLOR = "MESSAGE_COLOR";

    private static final String KEY_CODE = "CODE";

    private static final String KEY_LISTENER_ATTACHED = "LISTENER_ATTACHED";

    private static WarningDialog instance(Builder builder) {
        Bundle args = new Bundle();
        args.putInt(KEY_OK_TEXT, builder.okText);
        args.putInt(KEY_CANCEL_TEXT, builder.cancelText);
        args.putInt(KEY_MESSAGE_TEXT, builder.messageText);
        args.putString(KEY_MESSAGE_TEXT_STR, builder.messageTextStr);

        args.putBoolean(KEY_CANCELABLE, builder.cancelable);

        args.putInt(KEY_OK_COLOR, builder.okColor);
        args.putInt(KEY_CANCEL_COLOR, builder.cancelColor);
        args.putInt(KEY_MESSAGE_COLOR, builder.messageColor);

        args.putInt(KEY_CODE, builder.code);
        args.putBoolean(KEY_LISTENER_ATTACHED, builder.listenerAttached);

        WarningDialog dialogFragment = new WarningDialog();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @StringRes
    private int mOkText;
    @StringRes
    private int mCancelText;
    @StringRes
    private int mMessageText;

    private String mMessageTextStr;

    private boolean mCancelable;

    @ColorRes
    private int mOkColor;
    @ColorRes
    private int mCancelColor;
    @ColorRes
    private int mMessageColor;

    private int mCode;

    private OnWarningOkClickDialogListener mOkListener;
    private OnWarningDialogListener mBothListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mOkText = args.getInt(KEY_OK_TEXT);
            mCancelText = args.getInt(KEY_CANCEL_TEXT);
            mMessageText = args.getInt(KEY_MESSAGE_TEXT);
            mMessageTextStr = args.getString(KEY_MESSAGE_TEXT_STR);

            mCancelable = args.getBoolean(KEY_CANCELABLE);

            mOkColor = args.getInt(KEY_OK_COLOR);
            mCancelColor = args.getInt(KEY_CANCEL_COLOR);
            mMessageColor = args.getInt(KEY_MESSAGE_COLOR);

            mCode = args.getInt(KEY_CODE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(mCancelable);

        View rootView = inflater.inflate(R.layout.warning_dialog, container, false);

        TextView okView = (TextView) rootView.findViewById(R.id.btn_ok);
        TextView cancelView = (TextView) rootView.findViewById(R.id.btn_cancel);
        TextView messageView = (TextView) rootView.findViewById(R.id.tv_message);
        View dividerView = rootView.findViewById(R.id.v_divider);

        if (mOkText != NO_VALUE) {
            okView.setText(mOkText);
        }

        if (mOkColor != NO_VALUE) {
            okView.setTextColor(ContextCompat.getColor(getContext(), mOkColor));
        }

        if (mCancelText != NO_VALUE) {
            cancelView.setText(mCancelText);
        } else {
            cancelView.setVisibility(View.GONE);
            dividerView.setVisibility(View.GONE);
        }

        if (mCancelColor != NO_VALUE) {
            cancelView.setTextColor(ContextCompat.getColor(getContext(), mCancelColor));
        }

        if (mMessageText != NO_VALUE) {
            messageView.setText(mMessageText);
        }
        else if(mMessageTextStr != null){
            messageView.setText(mMessageTextStr);
        }

        if (mMessageColor != NO_VALUE) {
            messageView.setTextColor(ContextCompat.getColor(getContext(), mMessageColor));
        }

        okView.setOnClickListener(this);
        cancelView.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        boolean mListenerAttached = getArguments() != null && getArguments().getBoolean(KEY_LISTENER_ATTACHED);

        Fragment parentFragment = getParentFragment();
        if (mListenerAttached) {
            boolean attached = false;
            if (parentFragment != null) {
                if (parentFragment instanceof OnWarningDialogListener) {
                    mBothListener = (OnWarningDialogListener) parentFragment;
                    attached = true;
                } else if (parentFragment instanceof OnWarningOkClickDialogListener) {
                    mOkListener = (OnWarningOkClickDialogListener) parentFragment;
                    attached = true;
                }
            }

            if (!attached) {
                if (getActivity() instanceof OnWarningDialogListener) {
                    mBothListener = (OnWarningDialogListener) getActivity();
                    attached = true;
                } else if (getActivity() instanceof OnWarningOkClickDialogListener) {
                    mOkListener = (OnWarningOkClickDialogListener) getActivity();
                    attached = true;
                }
            }

            if (!attached) {
                throw new IllegalArgumentException("Warning dialog OnWarningOkClickDialogListener was marked but not implemented!");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOkListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            if (mOkListener != null) {
                mOkListener.onWarningDialogOkClicked(mCode);
            } else if (mBothListener != null) {
                mBothListener.onWarningDialogOkClicked(mCode);
            }
            dismissAllowingStateLoss();
        } else if (v.getId() == R.id.btn_cancel) {
            if (mBothListener != null) {
                mBothListener.onWarningDialogCancelClicked(mCode);
            }
            dismissAllowingStateLoss();
        }
    }


    public final static class Builder {
        @StringRes
        private int okText = NO_VALUE;
        @StringRes
        private int cancelText = NO_VALUE;
        @StringRes
        private int messageText = NO_VALUE;

        private String messageTextStr;

        private boolean cancelable;

        @ColorRes
        private int okColor = NO_VALUE;
        @ColorRes
        private int cancelColor = NO_VALUE;
        @ColorRes
        private int messageColor = NO_VALUE;

        private int code = NO_VALUE;
        private boolean listenerAttached;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setOkText(int okText) {
            this.okText = okText;
            return this;
        }

        public Builder setCancelText(int cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setMessageText(int messageText) {
            this.messageText = messageText;
            return this;
        }

        public Builder setMessageText(String messageText) {
            this.messageTextStr = messageText;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOkColor(int okColor) {
            this.okColor = okColor;
            return this;
        }

        public Builder setCancelColor(int cancelColor) {
            this.cancelColor = cancelColor;
            return this;
        }

        public Builder setMessageColor(int messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        /**
         * listener will be attached on dialogFragment onAttach called
         * this method just specifies contract to callback interface
         *
         * @param listener on buttons click callback
         * @return current builder instance
         */
        public Builder setListener(OnWarningDialogListener listener) {
            listenerAttached = listener != null;
            return this;
        }

        /**
         * listener will be attached on dialogFragment onAttach called
         * this method just specifies contract to callback interface
         *
         * @param listener on buttons click callback
         * @return current builder instance
         */
        public Builder setListener(OnWarningOkClickDialogListener listener) {
            listenerAttached = listener != null;
            return this;
        }

        public WarningDialog build() {
            return WarningDialog.instance(this);
        }
    }

    public interface OnWarningOkClickDialogListener {
        void onWarningDialogOkClicked(int dialogCode);
    }

    public interface OnWarningDialogListener extends OnWarningOkClickDialogListener {
        void onWarningDialogCancelClicked(int dialogCode);
    }
}
