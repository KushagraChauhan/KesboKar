package com.kesbokar.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ThirdTabAccessorAdapter extends FragmentPagerAdapter {

    ViewPager myViewPager;
    TabLayout myTabLayout;


    public ThirdTabAccessorAdapter(FragmentManager fm, ViewPager myViewPager, TabLayout myTabLayout)
    {
        super(fm);

        this.myViewPager=myViewPager;
        this.myTabLayout=myTabLayout;
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                BasicInfoBusinessFragment basicInfoBusinessFragment = new BasicInfoBusinessFragment(myViewPager,myTabLayout);
                return basicInfoBusinessFragment;


            case 1:
                DescriptionBusinessFragment descriptionBusinessFragment = new DescriptionBusinessFragment(myViewPager,myTabLayout);
                return descriptionBusinessFragment;

            case 2:
                SliderBusinessFragment sliderBusinessFragment = new SliderBusinessFragment(myViewPager, myTabLayout);
                return sliderBusinessFragment;

            case 3:
                PhotosBusinessFragment photosBusinessFragment = new PhotosBusinessFragment();
                return photosBusinessFragment;

            case 4:
                VideosBusinessFragment videosBusinessFragment = new VideosBusinessFragment(myViewPager,myTabLayout);
                return videosBusinessFragment;

            case 5:
                ServicesBusinessFragment servicesBusinessFragment = new ServicesBusinessFragment(myViewPager, myTabLayout);
                return servicesBusinessFragment;

            case 6:
                SocialLinkBusinessFragment socialLinkBusinessFragment = new SocialLinkBusinessFragment(myViewPager,myTabLayout);
                return socialLinkBusinessFragment;

            case 7:
                AccreditationBusinessFragment accreditationBusinessFragment = new AccreditationBusinessFragment();
                return accreditationBusinessFragment;

            case 8:
                BannersBusinessFragment bannersBusinessFragment = new BannersBusinessFragment();
                return bannersBusinessFragment;

            case 9:
                StatusBusinessFragment statusBusinessFragment = new StatusBusinessFragment(myViewPager,myTabLayout);
                return statusBusinessFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 10;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "BasicInfo";

            case 1:
                return "Description";

            case 2:
                return "Slider";

            case 3:
                return "Photos";

            case 4:
                return "Videos";

            case 5:
                return "Services";

            case 6:
                return "Social Links";

            case 7:
                return "Accreditation";

            case 8:
                return "Banners";

            case 9:
                return "Status";

            default:
                return null;
        }
    }
}


