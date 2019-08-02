package com.kesbokar.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabsAccessorAdapter extends FragmentPagerAdapter
{
    ViewPager viewPager;
    TabLayout tabLayout;

    public TabsAccessorAdapter(FragmentManager fm, ViewPager viewPager, TabLayout tabLayout)
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
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment(viewPager,tabLayout);
                return basicInfoFragment;


            case 1:
                ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment(viewPager,tabLayout);
                return contactDetailsFragment;

            case 2:
                DescriptionFragment descriptionFragment = new DescriptionFragment(viewPager,tabLayout);
                return descriptionFragment;

            case 3:
               PhotosFragment photosFragment = new PhotosFragment(viewPager,tabLayout);
               return photosFragment;

            case 4:
                AttributeFragment attributeFragment = new AttributeFragment(viewPager,tabLayout);
                return attributeFragment;

            case 5:
                StatusFragment statusFragment = new StatusFragment(viewPager,tabLayout);
                return statusFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 6;
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
                return "Contact Details";

            case 2:
                return "Description";

           case 3:
                return "Photos";

            case 4:
                return "Attribute";

            case 5:
                return "Status";

            default:
                return null;
        }
    }
}
