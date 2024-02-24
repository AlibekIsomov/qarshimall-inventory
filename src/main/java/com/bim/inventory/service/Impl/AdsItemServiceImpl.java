package com.bim.inventory.service.Impl;


import com.bim.inventory.dto.AdsItemDTO;
import com.bim.inventory.entity.AdsItem;
import com.bim.inventory.entity.FileEntity;
import com.bim.inventory.repository.AdsItemRepository;
import com.bim.inventory.repository.FileRepository;
import com.bim.inventory.service.AdsItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdsItemServiceImpl implements AdsItemService {
    private static final Logger logger = LoggerFactory.getLogger(AdsItemServiceImpl.class);
    @Autowired
    AdsItemRepository adsItemRepository;

    @Autowired
    FileRepository fileRepository;

    @Override
    public Page<AdsItem> getAll(Pageable pageable) throws Exception {
        return adsItemRepository.findAll(pageable);
    }

    @Override
    public Optional<AdsItem> getById(Long id) throws Exception {
        if(!adsItemRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
            return Optional.empty();
        }
        return adsItemRepository.findById(id);
    }


    @Override
    public Optional<AdsItem> create(AdsItemDTO data) throws Exception {

        AdsItem adsItem = new AdsItem();

        adsItem.setDescription(data.getDescription());
        adsItem.setSize(data.getSize());
        adsItem.setLocation(data.getLocation());

        return Optional.of(adsItemRepository.save(adsItem));
    }
    @Override
    public Optional<AdsItem> update(Long id, AdsItemDTO data) throws Exception {
        Optional<AdsItem> optionalAdsItem = adsItemRepository.findById(id);



        if (!optionalAdsItem.isPresent()) {
            logger.info("Store with id " + id + " does not exist");
            return Optional.empty();
        }
        AdsItem adsItem = optionalAdsItem.get();

        FileEntity oldFileEntity = adsItem.getFileEntity();

        // Check if fileId is provided before removing the FileEntity
        if (data.getFileEntityId() != null) {
            Optional<FileEntity> newFileEntityOptional = fileRepository.findById(data.getFileEntityId());

            if (!newFileEntityOptional.isPresent()) {
                logger.info("FileEntity with id " + data.getFileEntityId() + " does not exist");
                return Optional.empty();
            }

            // Set the new FileEntity
            FileEntity newFileEntity = newFileEntityOptional.get();
            adsItem.setFileEntity(newFileEntity);
        } else {
            // If fileId is not provided, remove the old FileEntity
            if (oldFileEntity != null) {
                // Delete the old FileEntity from the repository
                fileRepository.delete(oldFileEntity);
                // Remove the old FileEntity from the Store
                adsItem.setFileEntity(null);
            }
        }




            adsItem.setDescription(data.getDescription());
            adsItem.setSize(data.getSize());
            adsItem.setLocation(data.getLocation());

            // Save the updated category
            return Optional.of(adsItemRepository.save(adsItem));
    }


    @Override
    public void deleteById(Long id) {
        if(!adsItemRepository.existsById(id)) {
            logger.info("Input with id " + id + " does not exists");
        }

        adsItemRepository.deleteById(id);
    }
}
