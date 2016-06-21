package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.viewHolder.CommentEmptyHolder;
import com.example.zhaoting.myapplication.viewHolder.CommentExtraHolder;
import com.example.zhaoting.myapplication.viewHolder.CommentListHolder;
import com.example.zhaoting.myapplication.widget.onViewClickListener;
import com.example.zhaoting.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<CommentBean> mList = new ArrayList<>();
    private int longSize = 0;
    private int shortSize = 0;
    private onViewClickListener listener;

    public CommentListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            CommentExtraHolder holder = new CommentExtraHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_extra, parent, false));
            return holder;
        } else if (viewType == 1) {
            return new CommentEmptyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_empty, parent, false));
        } else {
            CommentListHolder holder = new CommentListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentExtraHolder) {
            if (position == 0) {
                ((CommentExtraHolder) holder).mTextView.setText(String.valueOf(longSize) + "条长评");
                ((CommentExtraHolder) holder).mImageView.setVisibility(View.GONE);
            } else {
                ((CommentExtraHolder) holder).mTextView.setText(String.valueOf(shortSize) + "条短评");
                ((CommentExtraHolder) holder).mImageView.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof CommentListHolder) {
            if (mList.size() != 0) {
                if (position < longSize + 1) {
                    Picasso.with(mContext).load(mList.get(position - 1).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                    ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 1).getAuthor());
                    ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 1).getContent());
                    String date = DateUtils.getInstance().dateLongToString(mList.get(position - 1).getTime());
                    ((CommentListHolder) holder).mCommentDate.setText(date);
                    ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 1).getLikes()));
                    if (mList.get(position - 1).getReply_to() != null) {
                        String name = mList.get(position - 1).getReply_to().getAuthor();
                        String content = mList.get(position - 1).getReply_to().getContent();
                        if (name != null) {
                            SpannableStringBuilder spannableString = new SpannableStringBuilder("//");
                            spannableString.append(name);
                            spannableString.append(":");
                            spannableString.append(content);
                            StyleSpan span = new StyleSpan(Typeface.BOLD);
                            int length = name.length() + 3;
                            spannableString.setSpan(span, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.black)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            ((CommentListHolder) holder).mCommentReply.setVisibility(View.VISIBLE);
                            ((CommentListHolder) holder).mCommentReply.setText(spannableString);
                        } else {
                            ((CommentListHolder) holder).mCommentReply.setText(mContext.getResources().getString(R.string.comment_delete));
                            if (Build.VERSION.SDK_INT >= 23) {
                                ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray, mContext.getTheme()));
                            } else {
                                ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
                            }
                        }

                        ((CommentListHolder) holder).mCommentReply.post(new Runnable() {
                            @Override
                            public void run() {
                                int count = ((CommentListHolder) holder).mCommentReply.getLineCount();
                                if (count > 2) {
                                    ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.VISIBLE);
                                } else {
                                    ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.GONE);
                                }
                            }
                        });

                    } else {
                        ((CommentListHolder) holder).mCommentReply.setVisibility(View.GONE);
                    }
                } else {
                    if (longSize == 0) {
                        Picasso.with(mContext).load(mList.get(position - 3).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                        ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 3).getAuthor());
                        ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 3).getContent());
                        String date = DateUtils.getInstance().dateLongToString(mList.get(position - 3).getTime());
                        ((CommentListHolder) holder).mCommentDate.setText(date);
                        ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 3).getLikes()));

                        if (mList.get(position - 3).getReply_to() != null) {
                            String name = mList.get(position - 3).getReply_to().getAuthor();
                            String content = mList.get(position - 3).getReply_to().getContent();
                            if (name != null) {
                                SpannableStringBuilder spannableString = new SpannableStringBuilder("//");
                                spannableString.append(name);
                                spannableString.append(":");
                                spannableString.append(content);
                                StyleSpan span = new StyleSpan(Typeface.BOLD);
                                int length = name.length() + 3;
                                spannableString.setSpan(span, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.black)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                ((CommentListHolder) holder).mCommentReply.setVisibility(View.VISIBLE);
                                ((CommentListHolder) holder).mCommentReply.setText(spannableString);
                            } else {
                                ((CommentListHolder) holder).mCommentReply.setText(mContext.getResources().getString(R.string.comment_delete));
                                if (Build.VERSION.SDK_INT >= 23) {
                                    ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray, mContext.getTheme()));
                                } else {
                                    ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
                                }
                            }

                            ((CommentListHolder) holder).mCommentReply.post(new Runnable() {
                                @Override
                                public void run() {
                                    int count = ((CommentListHolder) holder).mCommentReply.getLineCount();
                                    if (count > 2) {
                                        ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.VISIBLE);
                                    } else {
                                        ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            ((CommentListHolder) holder).mCommentReply.setVisibility(View.GONE);
                        }
                    } else {
                        Picasso.with(mContext).load(mList.get(position - 2).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                        ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 2).getAuthor());
                        ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 2).getContent());
                        String date = DateUtils.getInstance().dateLongToString(mList.get(position - 2).getTime());
                        ((CommentListHolder) holder).mCommentDate.setText(date);
                        ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 2).getLikes()));

                        if (mList.get(position - 2).getReply_to() != null) {
                            String name = mList.get(position - 2).getReply_to().getAuthor();
                            String content = mList.get(position - 2).getReply_to().getContent();
                            ((CommentListHolder) holder).mCommentReply.setVisibility(View.VISIBLE);
                            if (name != null) {
                                SpannableStringBuilder spannableString = new SpannableStringBuilder("//");
                                spannableString.append(name);
                                spannableString.append(":");
                                spannableString.append(content);
                                StyleSpan span = new StyleSpan(Typeface.BOLD);
                                int length = name.length() + 3;
                                spannableString.setSpan(span, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.black)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                ((CommentListHolder) holder).mCommentReply.setText(spannableString);
                            } else {
                                ((CommentListHolder) holder).mCommentReply.setText(mContext.getResources().getString(R.string.comment_delete));
                                if (Build.VERSION.SDK_INT >=23) {
                                    ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray, mContext.getTheme()));
                                } else {
                                    ((CommentListHolder) holder).mCommentReply.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
                                }
                            }

                            ((CommentListHolder) holder).mCommentReply.post(new Runnable() {
                                @Override
                                public void run() {
                                    int count = ((CommentListHolder) holder).mCommentReply.getLineCount();
                                    if (count > 2) {
                                        ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.VISIBLE);
                                    } else {
                                        ((CommentListHolder) holder).mCommentReplyFold.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            ((CommentListHolder) holder).mCommentReply.setVisibility(View.GONE);
                        }

                    }
                }
            }
        } else {

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            if (longSize == 0) {
                if (position == 1) {
                    return 1;
                } else if (position == 2) {
                    return 0;
                }
            } else {
                if (position == longSize + 1) {
                    return 0;
                } else {
                    return 2;
                }
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (longSize == 0 && mList.size() == 0) {
            return 3;
        } else {
            if (longSize == 0) {
                return mList.size() + 3;
            } else {
                return mList.size() + 2;
            }
        }
    }

    public void setLongSize(int longSize) {
        this.longSize = longSize;
    }

    public void setShortSize(int shortSize) {
        this.shortSize = shortSize;
    }

    public void setList(List<CommentBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(onViewClickListener listener) {
        this.listener = listener;
    }

}
