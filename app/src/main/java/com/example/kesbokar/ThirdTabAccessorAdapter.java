package com.example.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ThirdTabAccessorAdapter extends FragmentPagerAdapter {


    public ThirdTabAccessorAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                BasicInfoBusinessFragment basicInfoBusinessFragment = new BasicInfoBusinessFragment();
                return basicInfoBusinessFragment;


            case 1:
                DescriptionBusinessFragment descriptionBusinessFragment = new DescriptionBusinessFragment();
                return descriptionBusinessFragment;

            case 2:
                SliderBusinessFragment sliderBusinessFragment = new SliderBusinessFragment();
                return sliderBusinessFragment;

            case 3:
                PhotosBusinessFragment photosBusinessFragment = new PhotosBusinessFragment();
                return photosBusinessFragment;

            case 4:
                VideosBusinessFragment videosBusinessFragment = new VideosBusinessFragment();
                return videosBusinessFragment;

            case 5:
                ServicesBusinessFragment servicesBusinessFragment = new ServicesBusinessFragment();
                return servicesBusinessFragment;

            case 6:
                SocialLinkBusinessFragment socialLinkBusinessFragment = new SocialLinkBusinessFragment();
                return socialLinkBusinessFragment;

            case 7:
                AccreditationBusinessFragment accreditationBusinessFragment = new AccreditationBusinessFragment();
                return accreditationBusinessFragment;

            case 8:
                BannersBusinessFragment bannersBusinessFragment = new BannersBusinessFragment();
                return bannersBusinessFragment;

            case 9:
                StatusBusinessFragment statusBusinessFragment = new StatusBusinessFragment();
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


