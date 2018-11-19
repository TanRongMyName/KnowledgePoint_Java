package intentcompany.tanrong.com.knowledgepointset.Animator;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/7/31.
 */

public class FlyAnimator extends SimpleItemAnimator {
    List<RecyclerView.ViewHolder> removeHolders = new ArrayList<>();
    List<RecyclerView.ViewHolder> removeAnimators = new ArrayList<>();
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        removeHolders.add(holder);
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return false;
    }
    List<RecyclerView.ViewHolder> moveHolders = new ArrayList<>();
    List<RecyclerView.ViewHolder> moveAnimators = new ArrayList<>();
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        holder.itemView.setTranslationY(fromY - toY);
        moveHolders.add(holder);
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    @Override
    public void runPendingAnimations() {
        //飞出移动动画
        if(!removeHolders.isEmpty()) {
            for(RecyclerView.ViewHolder holder : removeHolders) {
                remove(holder);
            }
            removeHolders.clear();
        }
        //上移动动画
        if(!moveHolders.isEmpty()){
            for(RecyclerView.ViewHolder holder : moveHolders) {
                move(holder);
            }
            moveHolders.clear();
        }
    }
    private void remove(final RecyclerView.ViewHolder holder){
        removeAnimators.add(holder);
        TranslateAnimation animation = new TranslateAnimation(0, 1000, 0, 0);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dispatchRemoveStarting(holder);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                removeAnimators.remove(holder);
                dispatchRemoveFinished(holder);
                if(!isRunning()){
                    dispatchAnimationsFinished();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        holder.itemView.startAnimation(animation);
    }

    private void move(final RecyclerView.ViewHolder moveInfo){
        moveAnimators.add(moveInfo);
        ObjectAnimator animator = ObjectAnimator.ofFloat(moveInfo.itemView,
                "translationY", moveInfo.itemView.getTranslationY(), 0);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {
                dispatchMoveStarting(moveInfo);
            }

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                dispatchMoveFinished(moveInfo);
                moveAnimators.remove(moveInfo);
                if(!isRunning()) dispatchAnimationsFinished();
            }
        });
        animator.start();
    }





    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
         return !(removeHolders.isEmpty() && removeAnimators.isEmpty()&& moveHolders.isEmpty() && moveAnimators.isEmpty());
    }
}
