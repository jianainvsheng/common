package com.source.sdk.common.loading;

import com.source.sdk.common.loading.ball.ElasticBallBuilder;
import com.source.sdk.common.loading.ball.InfectionBallBuilder;
import com.source.sdk.common.loading.ball.IntertwineBuilder;
import com.source.sdk.common.loading.circle.DoubleCircleBuilder;
import com.source.sdk.common.loading.circle.PacManBuilder;
import com.source.sdk.common.loading.circle.RotateCircleBuilder;
import com.source.sdk.common.loading.circle.SingleCircleBuilder;
import com.source.sdk.common.loading.circle.SnakeCircleBuilder;
import com.source.sdk.common.loading.clock.CircleBuilder;
import com.source.sdk.common.loading.clock.ClockBuilder;
import com.source.sdk.common.loading.path.MusicPathBuilder;
import com.source.sdk.common.loading.path.SearchPathBuilder;
import com.source.sdk.common.loading.path.StairsPathBuilder;
import com.source.sdk.common.loading.rect.ChartRectBuilder;
import com.source.sdk.common.loading.rect.StairsRectBuilder;
import com.source.sdk.common.loading.star.LeafBuilder;
import com.source.sdk.common.loading.star.StarBuilder;
import com.source.sdk.common.loading.text.TextBuilder;

/**
 * Created by zyao89 on 2017/3/19.
 * Contact me at 305161066@qq.com or zyao89@gmail.com
 * For more projects: https://github.com/zyao89
 * My Blog: http://zyao89.me
 */
public enum Z_TYPE
{
    CIRCLE(CircleBuilder.class),
    CIRCLE_CLOCK(ClockBuilder.class),
    STAR_LOADING(StarBuilder.class),
    LEAF_ROTATE(LeafBuilder.class),
    DOUBLE_CIRCLE(DoubleCircleBuilder.class),
    PAC_MAN(PacManBuilder.class),
    ELASTIC_BALL(ElasticBallBuilder.class),
    INFECTION_BALL(InfectionBallBuilder.class),
    INTERTWINE(IntertwineBuilder.class),
    TEXT(TextBuilder.class),
    SEARCH_PATH(SearchPathBuilder.class),
    ROTATE_CIRCLE(RotateCircleBuilder.class),
    SINGLE_CIRCLE(SingleCircleBuilder.class),
    SNAKE_CIRCLE(SnakeCircleBuilder.class),
    STAIRS_PATH(StairsPathBuilder.class),
    MUSIC_PATH(MusicPathBuilder.class),
    STAIRS_RECT(StairsRectBuilder.class),
    CHART_RECT(ChartRectBuilder.class),;

    private final Class<?> mBuilderClass;

    Z_TYPE(Class<?> builderClass)
    {
        this.mBuilderClass = builderClass;
    }

    <T extends ZLoadingBuilder> T newInstance()
    {
        try
        {
            return (T) mBuilderClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
