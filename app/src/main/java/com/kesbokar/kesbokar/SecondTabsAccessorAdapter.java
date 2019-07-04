package com.kesbokar.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class SecondTabsAccessorAdapter extends FragmentPagerAdapter {
    ViewPager viewPager;
    TabLayout tabLayout;
    public SecondTabsAccessorAdapter(FragmentManager fm, ViewPager viewPager, TabLayout tabLayout)
    {
        super(fm);
        this.viewPager=viewPager;
        this.tabLayout=tabLayout;
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                CarDetailsFragment carDetailsFragment = new CarDetailsFragment(viewPager,tabLayout);
                return  carDetailsFragment;

            case 1:
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment(viewPager,tabLayout);
                return basicInfoFragment;


            case 2:
                ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment(viewPager,tabLayout);
                return contactDetailsFragment;

            case 3:
                DescriptionFragment descriptionFragment = new DescriptionFragment(viewPager,tabLayout);
                return descriptionFragment;

            case 4:
                PhotosFragment photosFragment = new PhotosFragment(viewPager,tabLayout);
                return photosFragment;

            case 5:
                AttributeFragment attributeFragment = new AttributeFragment(viewPager,tabLayout);
                return attributeFragment;

            case 6:
                StatusFragment statusFragment = new StatusFragment(viewPager,tabLayout);
                return statusFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "CarDetails";

            case 1:
                return "BasicInfo";

            case 2:
                return "ContactDetails";

            case 3:
                return "Description";

            case 4:
                return "Photos";

            case 5:
                return "Attribute";

            case 6:
                return "Status";

            default:
                return null;
        }
    }
}
