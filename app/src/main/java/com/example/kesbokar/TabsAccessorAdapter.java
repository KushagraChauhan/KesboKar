package com.example.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessorAdapter extends FragmentPagerAdapter
{

    public TabsAccessorAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment();
                return basicInfoFragment;


            case 1:
                ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
                return contactDetailsFragment;

            case 2:
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                return descriptionFragment;

            case 3:
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;

            case 4:
                AttributeFragment attributeFragment = new AttributeFragment();
                return attributeFragment;

            case 5:
                StatusFragment statusFragment = new StatusFragment();
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
                return "ContactDetails";

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
